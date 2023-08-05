package com.company.springboot.demo.myspringboot.global;

import java.util.ArrayList;
import java.util.List;

import com.company.springboot.demo.myspringboot.entity.Product;

public class GlobalData {

	public static List<Product>cart;
	static {
		cart=new ArrayList<>();
	}
}
