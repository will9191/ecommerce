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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    private ModelMapper modelMapper;
    private ApplicationAuditAware auditAware;

    public CartResponse addItemToCart(CartItemDto cartItemDto, Product product, User user) {
        Cart cart = user.getCart();

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setCartItems(new HashSet<>());
        }

        Set<CartItem> cartItemsList = cart.getCartItems();

        boolean itemAlreadyExists = false;
        for (CartItem existingCart : cartItemsList) {
            if (existingCart.getProduct().equals(product) && existingCart.getSize().getName().equals(cartItemDto.getSize().getName())) {
                Size size = existingCart.getSize();
                size.setQuantity(existingCart.getSize().getQuantity() + cartItemDto.getSize().getQuantity());
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
            cartItemRepository.save(cartItem);
            cartItemsList.add(cartItem);
        }

        cart.setCartItems(cartItemsList);
        user.setCart(cart);
        cartRepository.save(cart);
        userRepository.save(user);

        return CartResponse.builder().cartItems(cartItemsList).cartId(cart.getCartId()).build();
    }

    public void removeCartItem(Long cartId) {
        cartRepository.deleteById(cartId);
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

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }
}
