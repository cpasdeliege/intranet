package be.cpasdeliege.intranet.DemServInfo.model;

public class TypeDemandeDsi {

	String type = "";
	boolean accordDG = true;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAccordDG() {
		return accordDG;
	}

	public void setAccordDG(boolean accordDG) {
		this.accordDG = accordDG;
	}

	public String getTypeChoix1() {
		return type.split("-")[0];
	}

	public String getTypeChoix2() {
		if (type.split("-").length > 1) {
			return type.split("-")[1];
		}
		return "";
	}

	public String getTypeChoix3() {
		if (type.split("-").length > 2) {
			return type.split("-")[2];
		}
		return "";
	}

}
