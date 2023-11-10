package com.util;

public class FormatUtil
{
	public static String formatCpf(String cpf)
	{
		return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
	}
	
	public static String formatCnpj(String cnpj)
	{
		return cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) +
	               "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12);
	}
	
	public static String formatRg(String rg)
	{
		if (rg.length() == 9)
			return rg.substring(0, 2) + "." + rg.substring(2, 5) + "." + rg.substring(5, 8) + "-" + rg.substring(8);
		
		return rg.substring(0, 2) + "." + rg.substring(2, 5) + "." + rg.substring(5, 8);
	}
}
