package services;

import entity.Pet;
import repository.PetRepository;

import java.sql.SQLException;
import java.util.List;

public class PetService {
    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getOwnerPets(long ownerId) throws SQLException {
        return petRepository.getByOwnerId(ownerId);
    }

    public Pet getById(long id) throws SQLException {
        return petRepository.get(id);
    }

    public void deletePet(long id, long owner_id) throws SQLException {
        if (petRepository.get(id).getOwnerId() == owner_id){
            petRepository.delete(id);
        }
    }

    public void updatePet(Pet pet) throws SQLException {
        if (petRepository.get(pet.getId()).getOwnerId() == pet.getOwnerId()){
            petRepository.update(pet);
        }
    }

    public void addPet(Pet pet) throws SQLException {
        petRepository.insert(pet);
    }
}
