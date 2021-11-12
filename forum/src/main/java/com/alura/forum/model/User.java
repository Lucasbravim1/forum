package com.alura.forum.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.alura.forum.dto.RegisterUserDto;
import com.alura.forum.repository.UserProfileRepository;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	private static final long serialVersionUID = -2185662887514446549L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String password;

	private Integer age;

	private String email;

	private LocalDate entryDate;

	private Integer solvedTopics;

	@ManyToOne(fetch = FetchType.EAGER)
	private UserProfile userProfile;

	public User() {

	}

	public User(RegisterUserDto registerUserDto, UserProfileRepository userProfileRepository) {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodePassword = bCryptPasswordEncoder.encode(registerUserDto.getPassword());

		this.name = registerUserDto.getName();
		this.email = registerUserDto.getEmail();
		this.age = registerUserDto.getAge();
		this.entryDate = LocalDate.now();
		this.password = encodePassword;
		this.solvedTopics = 0;

		Optional<UserProfile> visitant = userProfileRepository.findById((long) 2);
		if (registerUserDto.getUserProfile() == null & visitant.isPresent()) {
			this.userProfile = visitant.get();
		} else {
			this.userProfile = registerUserDto.getUserProfile();
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}

	public Integer getSolvedTopics() {
		return solvedTopics;
	}

	public void setSolvedTopics(Integer solvedTopics) {
		this.solvedTopics = solvedTopics;
	}

//	public List<UserProfile> getUserProfile() {
//		return userProfile;
//	}

	public static void setSerialversionuid(long serialversionuid) {
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", age=" + age + ", email=" + email
				+ ", entryDate=" + entryDate + ", solvedTopics=" + solvedTopics + "]";
	}

}
