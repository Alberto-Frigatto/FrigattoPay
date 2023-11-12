package com.model.entity;

public class Tipo 
{
	private int id;
	private String nome;
	
	public Tipo(
		int id,
		String nome
	) 
	{
		this.id = id;
		this.nome = nome;
	}

	public int getId() 
	{
		return id;
	}


	public String getNome() 
	{
		return nome;
	}

}
