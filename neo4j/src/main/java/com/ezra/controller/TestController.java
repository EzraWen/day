package com.ezra.controller;

import com.ezra.dto.MaterialToUserDTO;
import com.ezra.dto.UserToCustomerDTO;
import com.ezra.entity.*;
import com.ezra.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private MaterialRepository materialRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private BelongRepository belongRepository;


    @Resource
    private UserForwardRepository userForwardRepository;
    @Resource
    private CustomerForwardRepository customerForwardRepository;
    @Resource
    private CustomerRepository customerRepository;

    /**
     * 创建素材到用户的关系
     */
    @GetMapping("/materialToUser")
    public void create(@RequestBody MaterialToUserDTO dto){

        MaterialToUserDTO.MaterialInfo materialInfo = dto.getMaterialInfo();
        Material material = new Material();
        material.setEntityId(materialInfo.getMaterialId());
        material.setName(materialInfo.getName());
        material.setCorpId(materialInfo.getCorpId());
        material.setMaterialType(materialInfo.getMaterialType());
        materialRepository.save(material);

        MaterialToUserDTO.UserInfo userInfo = dto.getUserInfo();

        User user = new User();
        user.setEntityId(userInfo.getUserId());
        user.setCorpId(userInfo.getCorpId());
        userRepository.save(user);


        Belong belong = new Belong();
        belong.setMaterial(material);
        belong.setUser(user);

        belongRepository.save(belong);
    }


    @GetMapping("/userForwardCustomer")
    public void create1(@RequestBody UserToCustomerDTO dto){
        UserToCustomerDTO.UserInfo userInfo = dto.getUserInfo();
        User user = new User();
        user.setEntityId(userInfo.getUserId());
        user.setCorpId(userInfo.getCorpId());
        userRepository.save(user);

        UserToCustomerDTO.CustomerInfo customerInfo = dto.getCustomerInfo();
        Customer customer = new Customer();
        customer.setEntityId(customerInfo.getCustomerId());
        customer.setCorpId(customerInfo.getCorpId());
        customerRepository.save(customer);

        UserForward userForward = new UserForward();
        userForward.setId(20L);
        userForward.setUser(user);
        userForward.setCustomer(customer);
        userForward.setStart(user.getEntityId());
        userForward.setEnd(customer.getEntityId());
        userForward.setMaterialId(dto.getMaterialId());

        userForwardRepository.save(userForward);
    }
}
