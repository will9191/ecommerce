package com.example.ecommerce.cart;

import com.example.ecommerce.auditing.ApplicationAuditAware;

import com.example.ecommerce.product.Product;
import com.example.ecommerce.product.ProductRepository;

import com.example.ecommerce.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    private ModelMapper modelMapper;
    private ApplicationAuditAware auditAware;

    public Cart addItemToCart(CartDto cartDto, Product product, User user) {

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setSize(cartDto.getSize());
        cart.setPrice(product.getPrice());

        return cartRepository.save(cart);
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
