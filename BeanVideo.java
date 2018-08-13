package com.logic;

import java.util.HashMap;
import java.util.LinkedList;

public class BeanVideo implements VideoLocations
{
	public HashMap getList()
	{
		System.out.println("Method Call :: "+videoloc);
		return videoloc;
	}
}
