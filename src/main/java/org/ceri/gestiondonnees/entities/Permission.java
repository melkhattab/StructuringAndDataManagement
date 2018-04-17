package org.ceri.gestiondonnees.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name="Permission.findPermission", 
			query="select p from Permission p "
					+ "where p.readPermission = :r and p.writePermission = :w ")
})
public class Permission implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1071152164586265042L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_permission")
	private int idPermission ;
	
	private Boolean readPermission ; 
	private Boolean writePermission ; 
	private Boolean updatePermission ; 
	private Boolean deletePermission ;
	
	@OneToMany(mappedBy="permission")
	private Collection<User> users ;
	
	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Permission(Boolean readPermission, Boolean writePermission, Boolean updatePermission,
			Boolean deletePermission) {
		super();
		this.readPermission = readPermission;
		this.writePermission = writePermission;
		this.updatePermission = updatePermission;
		this.deletePermission = deletePermission;
	}

	public Boolean getReadPermission() {
		return readPermission;
	}

	public Boolean getWritePermission() {
		return writePermission;
	}

	public Boolean getUpdatePermission() {
		return updatePermission;
	}

	public Boolean getDeletePermission() {
		return deletePermission;
	}

	public void setReadPermission(Boolean readPermission) {
		this.readPermission = readPermission;
	}

	public void setWritePermission(Boolean writePermission) {
		this.writePermission = writePermission;
	}

	public void setUpdatePermission(Boolean updatePermission) {
		this.updatePermission = updatePermission;
	}

	public void setDeletePermission(Boolean deletePermission) {
		this.deletePermission = deletePermission;
	}

	public boolean equals(Permission droit) {
		return true ;
	}
	

	
}
