package com.example.ecommerce.cart;

import com.example.ecommerce.auditing.ApplicationAuditAware;

import com.example.ecommerce.cartItem.CartItem;
import com.example.ecommerce.cartItem.CartItemDto;
import com.example.ecommerce.cartItem.CartItemRepository;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.product.ProductRepository;

import com.example.ecommerce.size.Size;
import com.example.ecommerce.user.User;
import com.example.ecommerce.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import org.modelmapper.ModelMapper;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    private ApplicationAuditAware auditAware;

    public ResponseEntity<?> addItemToCart(CartItemDto cartItemDto, Product product, User user) {
        List<Size> productSizes = product.getSizes();
        Size productSize = productSizes.stream().filter(psize -> psize.getName().equals(cartItemDto.getSize().getName())).findFirst().orElseThrow(() -> new IllegalArgumentException("Size not found"));
        if (productSize.getQuantity() <= 0) {
            throw new RuntimeException("This size is out of stock");
        }
        Cart cart = cartRepository.findByUser(user);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setCartItems(new ArrayList<>());
        }

        List<CartItem> cartItemsList = cart.getCartItems();

        boolean itemAlreadyExists = false;
        for (CartItem existingCart : cartItemsList) {
            if (existingCart.getProduct().equals(product) && existingCart.getSize().getName().equals(cartItemDto.getSize().getName())) {
                Size size = existingCart.getSize();
                size.setQuantity(existingCart.getSize().getQuantity() + cartItemDto.getSize().getQuantity());
                this.removeProductSizeQuantity(product, existingCart);
                existingCart.setSize(size);
                itemAlreadyExists = true;
                break;
            }
        }

        if (!itemAlreadyExists) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setSize(cartItemDto.getSize());
            cartItem.setCart(cart);
            cartItem.setPrice(product.getPrice());
            this.removeProductSizeQuantity(product, cartItem);
            cartItemRepository.save(cartItem);
            cartItemsList.add(cartItem);
        }

        cart.setCartItems(cartItemsList);
        cart.setTotalPrice(this.getTotalPrice(cartItemsList));
        cartRepository.save(cart);

        CartProductDTO response = new CartProductDTO(cart, product);

        return ResponseEntity.ok(response);
    }

    public void removeProductSizeQuantity(Product product, CartItem cartItem) {
        List<Size> productSizes = product.getSizes();
        Size productSize = productSizes.stream().filter(psize -> psize.getName().equals(cartItem.getSize().getName())).findFirst().orElseThrow(() -> new IllegalArgumentException("Size not found"));
        if (productSize.getQuantity() - cartItem.getSize().getQuantity() >= 0) {
            productSize.setQuantity(productSize.getQuantity() - cartItem.getSize().getQuantity());
            product.setSizes(productSizes);
        } else {
            throw new RuntimeException("Size not available");
        }

    }

    public void addProductSizeQuantity(Product product, CartItem cartItem) {
        List<Size> productSizes = product.getSizes();
        Size productSize = productSizes.stream().filter(psize -> psize.getName().equals(cartItem.getSize().getName())).findFirst().orElseThrow(() -> new IllegalArgumentException("Size not found"));
        productSize.setQuantity(productSize.getQuantity() + cartItem.getSize().getQuantity());
        product.setSizes(productSizes);
    }


    public ResponseEntity<?> removeCartItem(Long cartId, User user) {
        Cart cart = cartRepository.findByUser(user);
        Optional<CartItem> cartItem = cartItemRepository.findById(cartId);
        if (cartItem.isPresent()) {
            var cartItemExists = cartItem.get();
            cart.getCartItems().remove(cartItemExists);
            cart.setTotalPrice(this.getTotalPrice(cart.getCartItems()));
            this.addProductSizeQuantity(cartItemExists.getProduct(), cartItemExists);
            cartRepository.save(cart);
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<?> removeQuantity(Long cartId, User user) {
        Cart cart = cartRepository.findByUser(user);
        CartItem cartItem = cartItemRepository.findById(cartId).orElse(null);

        assert cartItem != null;
        Size size = cartItem.getSize();

        if (size.getQuantity() == 1) {
            cart.getCartItems().remove(cartItem);
        } else {
            size.setQuantity(size.getQuantity() - 1);
            cartItem.setSize(size);
        }
        cart.setTotalPrice(this.getTotalPrice(cart.getCartItems()));
        this.addProductSizeQuantity(cartItem.getProduct(), cartItem);
        cartRepository.save(cart);
        return ResponseEntity.ok(cart);
    }

    public ResponseEntity<?> addQuantity(Long cartId, User user) {
        Cart cart = cartRepository.findByUser(user);
        CartItem cartItem = cartItemRepository.findById(cartId).orElse(null);
        assert cartItem != null;
        Size size = cartItem.getSize();
        size.setQuantity(size.getQuantity() + 1);
        cartItem.setSize(size);
        cart.setTotalPrice(this.getTotalPrice(cart.getCartItems()));
        this.removeProductSizeQuantity(cartItem.getProduct(), cartItem);
        cartRepository.save(cart);
        return ResponseEntity.ok(cart);
    }

    public ResponseEntity<?> getCartByUser(User user) {
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setUser(user);
            cart = newCart;

            cartRepository.save(cart);
        }
        return ResponseEntity.ok(cart);
    }


//    public CartDto getCart(String emailId, Long cartId) {
//        Cart cart = cartRepository.findCartByEmailAndCartId(emailId, cartId);
//
//        if (cart == null) {
//            throw new ResourceNotFoundException("Cart", "cartId", cartId);
//        }
//
//        CartDto cartDto = modelMapper.map(cart, CartDto.class);
//
//        List<ProductDto> products = cart.getCartItems().stream()
//                .map(p -> modelMapper.map(p.getProduct(), ProductDto.class)).collect(Collectors.toList());
//
//        cartDto.setProducts(products);
//
//        return cartDto;
//    }

    public Double getTotalPrice(List<CartItem> cartItems) {
        var totalPrice = 0.0;
        for (CartItem cartItem : cartItems) {
            var quantity = cartItem.getSize().getQuantity();

            totalPrice += cartItem.getPrice() * quantity;
        }
        return totalPrice;
    }

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }
}
