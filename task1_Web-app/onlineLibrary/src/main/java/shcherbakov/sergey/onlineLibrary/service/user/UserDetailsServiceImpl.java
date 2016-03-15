package shcherbakov.sergey.onlineLibrary.service.user;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shcherbakov.sergey.onlineLibrary.dao.user.UserDao;
import shcherbakov.sergey.onlineLibrary.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	private UserDao userDao;

    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
	
    @Transactional
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = this.userDao.find(email);
		if (user == null)
			throw new UsernameNotFoundException("User with given email " + email + " isn't found. ");
		
		return new UserDetailsImpl(user);
	}

	private static class UserDetailsImpl extends User implements UserDetails {
		
		private static final long serialVersionUID = 1L;
		
		private static final Collection<GrantedAuthority> userAuthorities, adminAuthorities;

		static {
			Collection<GrantedAuthority> tempCollection;

			tempCollection = new LinkedList<GrantedAuthority>();
			tempCollection.add(new SimpleGrantedAuthority("USER"));
			userAuthorities = Collections.unmodifiableCollection(tempCollection);

			tempCollection = new LinkedList<GrantedAuthority>();
			tempCollection.add(new SimpleGrantedAuthority("USER"));
			tempCollection.add(new SimpleGrantedAuthority("ADMIN"));
			adminAuthorities = Collections.unmodifiableCollection(tempCollection);
		}
		
		public UserDetailsImpl(User user){
			super(user.getEmail(), user.getUserPassword(), user.getSurname(), user.getName(), user.getUserRole());
		}
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			if(this.getUserRole().equals("ADMIN"))
				return adminAuthorities; 
			return userAuthorities;
		}

		@Override
		public String getUsername() { return getEmail(); }
		
		@Override
		public String getPassword() { return getUserPassword(); }
		
		@Override
		public boolean isAccountNonExpired() { return true; }

		@Override
		public boolean isAccountNonLocked() { return true; }

		@Override
		public boolean isCredentialsNonExpired() { return true; }

		@Override
		public boolean isEnabled() { return true; }
	}
}
