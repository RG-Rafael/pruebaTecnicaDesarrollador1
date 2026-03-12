package com.mx.PruebaTecD1.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mx.PruebaTecD1.model.Address;
import com.mx.PruebaTecD1.model.User;
import com.mx.PruebaTecD1.util.AESUtil;
import com.mx.PruebaTecD1.util.ValidationUtil;

@Service
public class UserService {

	private List<User> users = new ArrayList<>();

	public UserService(){
		//Usuario número 1
        User user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setEmail("user1@mail.com");
        user1.setName("user1");
        user1.setPhone("+1 55 555 555 55");
        user1.setPassword("7c4a8d09ca3762af61e59520943dc26494f8941b");
        user1.setTaxId("AARR990101XXX");
        user1.setCreatedAt("01-01-2026 00:00:00");

        Address work = new Address();
        
        work.setId(1L);
        work.setName("workaddress");
        work.setStreet("street No. 1");
        work.setCountryCode("UK");
        
        Address home = new Address();
        
        home.setId(2L);
        home.setName("homeaddress");
        home.setStreet("street No. 2");
        home.setCountryCode("AU");
        
        List<Address> addresses = new ArrayList<>();
        addresses.add(work);
        addresses.add(home);

        user1.setAddresses(addresses);
        users.add(user1);
        
      //Usuario número 2
        User user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setEmail("user2@mail.com");
        user2.setName("user2");
        user2.setPhone("+1 55 555 555 56");
        user2.setPassword("123456");
        user2.setTaxId("AARR990101YYY");
        user2.setCreatedAt("01-01-2026 00:00:00");

        Address work2 = new Address();
        work2.setId(1L);
        work2.setName("workaddress");
        work2.setStreet("street No. 3");
        work2.setCountryCode("US");

        Address home2 = new Address();
        home2.setId(2L);
        home2.setName("homeaddress");
        home2.setStreet("street No. 4");
        home2.setCountryCode("CA");

        List<Address> addresses2 = new ArrayList<>();
        addresses2.add(work2);
        addresses2.add(home2);

        user2.setAddresses(addresses2);

        users.add(user2);


      //Usuario número 3
        User user3 = new User();
        user3.setId(UUID.randomUUID());
        user3.setEmail("user3@mail.com");
        user3.setName("user3");
        user3.setPhone("+1 55 555 555 57");
        user3.setPassword("123456");
        user3.setTaxId("AARR990101ZZZ");
        user3.setCreatedAt("01-01-2026 00:00:00");

        Address work3 = new Address();
        work3.setId(1L);
        work3.setName("workaddress");
        work3.setStreet("street No. 5");
        work3.setCountryCode("ES");

        Address home3 = new Address();
        home3.setId(2L);
        home3.setName("homeaddress");
        home3.setStreet("street No. 6");
        home3.setCountryCode("MX");

        List<Address> addresses3 = new ArrayList<>();
        addresses3.add(work3);
        addresses3.add(home3);

        user3.setAddresses(addresses3);

        users.add(user3);
    }

	//Metdo para listar & ordenar
	public List<User> getUsersSorted(String sortedBy){

	    if(sortedBy == null){
	        return users;
	    }

	    if(sortedBy.equals("email")){
	        users.sort(Comparator.comparing(User::getEmail));
	    }
	    
	    if(sortedBy.equals("id")){
	        users.sort(Comparator.comparing(User::getId));
	    }

	    if(sortedBy.equals("name")){
	        users.sort(Comparator.comparing(User::getName));
	    }

	    if(sortedBy.equals("phone")){
	        users.sort(Comparator.comparing(User::getPhone));
	    }

	    if(sortedBy.equals("tax_id")){
	        users.sort(Comparator.comparing(User::getTaxId));
	    }

	    if(sortedBy.equals("created_at")){
	        users.sort(Comparator.comparing(User::getCreatedAt));
	    }

	    return users;
	}

	//Metodo para filtrar
	public List<User> filterUsers(String filter){

	    String[] parts = filter.split(":");
	    
	    if(parts.length != 3){
	        throw new RuntimeException("Formato de filtro incorrecto");
	    }
	    
	    String field = parts[0];
	    String operator = parts[1];
	    String value = parts[2];

	    List<User> result = new ArrayList<>();

	    for(User user : users){

	        String fieldValue = "";

	        if(field.equals("email")){
	            fieldValue = user.getEmail();
	        }

	        if(field.equals("name")){
	            fieldValue = user.getName();
	        }

	        if(field.equals("phone")){
	            fieldValue = user.getPhone();
	        }

	        if(field.equals("tax_id")){
	            fieldValue = user.getTaxId();
	        }

	        if(field.equals("created_at")){
	            fieldValue = user.getCreatedAt();
	        }

	        if(field.equals("id")){
	            fieldValue = user.getId().toString();
	        }


	        if(operator.equals("co") && fieldValue.contains(value)){
	            result.add(user);
	        }

	        if(operator.equals("eq") && fieldValue.equals(value)){
	            result.add(user);
	        }

	        if(operator.equals("sw") && fieldValue.startsWith(value)){
	            result.add(user);
	        }

	        if(operator.equals("ew") && fieldValue.endsWith(value)){
	            result.add(user);
	        }

	    }

	    return result;
	}

	//Metodo para crear
	public User createUser(User user){

	    user.setId(UUID.randomUUID());

	    if(!ValidationUtil.isValidRFC(user.getTaxId())){
	        throw new RuntimeException("RFC inválido");
	    }

	    if(!ValidationUtil.isValidPhone(user.getPhone())){
	        throw new RuntimeException("Phone inválido");
	    }

	    for(User u : users){
	        if(u.getTaxId().equals(user.getTaxId())){
	            throw new RuntimeException("tax_id ya existe");
	        }
	    }

	    user.setPassword(AESUtil.encrypt(user.getPassword()));

	    ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Indian/Antananarivo"));
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

	    user.setCreatedAt(now.format(formatter));

	    users.add(user);
	    return user;
	}
	
	//Metodo para actualizar
	public User updatedUser(UUID id, User updatedUser) {
		for (User user : users) {

	        if (user.getId().equals(id)) {

	            if (updatedUser.getEmail() != null) {
	                user.setEmail(updatedUser.getEmail());
	            }

	            if (updatedUser.getName() != null) {
	                user.setName(updatedUser.getName());
	            }

	            if (updatedUser.getPhone() != null) {
	                user.setPhone(updatedUser.getPhone());
	            }

	            if (updatedUser.getPassword() != null) {
	                user.setPassword(updatedUser.getPassword());
	            }

	            if (updatedUser.getTaxId() != null) {
	                user.setTaxId(updatedUser.getTaxId());
	            }

	            if (updatedUser.getCreatedAt() != null) {
	                user.setCreatedAt(updatedUser.getCreatedAt());
	            }

	            if (updatedUser.getAddresses() != null) {
	                user.setAddresses(updatedUser.getAddresses());
	            }

	            return user;
	        }
	    }

	    return null;
	}

	//metodo para eliminar
	public String deleteUser(UUID id){
		for(User user : users){

	        if(user.getId().equals(id)){
	            users.remove(user);
	            return "Usuario eliminado correctamente";
	        }

	    }

	    return "Usuario no encontrado";
	}

	//metodo para login
	public User login(String taxId, String password){

	    for(User user : users){

	        if(user.getTaxId().equals(taxId)){

	            String encryptedPassword = AESUtil.encrypt(password);

	            if(user.getPassword() != null && user.getPassword().equals(encryptedPassword)){
	                return user;
	            }
	        }
	    }

	    throw new RuntimeException("Credenciales incorrectas");
	}
}
