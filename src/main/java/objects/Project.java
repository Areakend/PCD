package objects;

import java.time.LocalDate;

public class Project {
	private String titre, prefix; 
	private LocalDate dateFin;
	
	public Project(String titre,String prefix, LocalDate deadline) {
		this.titre=titre;
		this.prefix=prefix;
		this.dateFin=deadline;
	}
	public String getTitre() {
		return titre;
	}
	public String getPrefix() {
		return prefix;
	}
	public LocalDate getDateFin() {
		return dateFin;
	}

}
