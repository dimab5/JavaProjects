package com.ownerMicroservice.kafka;

import com.entities.owners.Owner;
import com.entities.owners.OwnerDto;
import com.ownerMicroservice.services.OwnerMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final OwnerMapper ownerMapper;

    public void checkOwnerExists(Owner owner) {
        kafkaTemplate.send("check_owner_exists_response", String.valueOf(ownerMapper.ownerToDto(owner)));
    }

    public void getAllOwners(String owners) {
        kafkaTemplate.send("get_all_owners_response", owners);
    }

    public void getOwnerById(OwnerDto ownerDto) {
        kafkaTemplate.send("get_owner_by_id_response", String.valueOf(ownerDto));
    }

    public void deleteOwnerById(OwnerDto ownerDto) {
        kafkaTemplate.send("delete_owner_by_id_response", String.valueOf(ownerDto));
    }
}
