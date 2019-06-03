package br.fjn.edu.biblioteca.model;

public enum Level {
	EMPLOYEE("EMPLOYEE"), MANAGER("MANAGER");

	public String level;

	Level(String level) {
		this.level = level;
	}

	public String getLevel() {
		return this.level;
	}

}
