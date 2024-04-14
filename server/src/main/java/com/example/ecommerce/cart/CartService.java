package com.example.ecommerce.cart;

import com.example.ecommerce.auditing.ApplicationAuditAware;
import com.example.ecommerce.cartItem.CartItem;
import com.example.ecommerce.cartItem.CartItemDto;
import com.example.ecommerce.cartItem.CartItemRepository;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.product.ProductRepository;

import com.example.ecommerce.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import org.modelmapper.ModelMapper;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private ModelMapper modelMapper;
    private ApplicationAuditAware auditAware;

    public Cart addItemToCart(CartItemDto cartItemDto, Product product, User user) {
        Cart cart = user.getCart();

        if (cart == null) {
            cart = new Cart();
        }

        Set<CartItem> cartItemList = new HashSet<>();

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setSize(cartItemDto.getSize());

        cartItemRepository.save(cartItem);

        cartItemList.add(cartItem);

        cart.setCartItems(cartItemList);
        cart.setUser(user);
        cart.setTotalPrice(50);

        return cartRepository.save(cart);
    }

    public List<CartItem> getCartItem() {
        return cartItemRepository.findAll();
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
