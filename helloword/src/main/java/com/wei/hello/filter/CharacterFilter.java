package com.wei.hello.filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.annotation.WebFilter;

/**
 * Created by weiwei on 2021/4/22.
 * @author weiwei
 */
@Order(1)
@WebFilter(filterName = "characterFilter", urlPatterns = {"/*"})
public class CharacterFilter extends CharacterEncodingFilter {

}
