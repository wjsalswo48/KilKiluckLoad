package mz.sixsense.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.sixsense.shop.entity.Delivery;
import mz.sixsense.shop.repository.DeliveryRepository;
import mz.sixsense.shop.service.DeliveryService;

@Service
public class DeliveryServiceImpl implements DeliveryService{
	
	@Autowired
	private DeliveryRepository deliveryRepo;
	
	
	@Override
	public Delivery getDelivery(long deliveryID) {
		Delivery del = deliveryRepo.findById(deliveryID).get();
		
		return del;
	}
	
	
	@Override
	public void deleteDelivery(long deliveryID) {
		deliveryRepo.deleteById(deliveryID);
	}

	@Override
	public void insertDelivery(Delivery delivery) {
		deliveryRepo.save(delivery);
		
	}
	
	@Override
	public void updateDelivery(Delivery delivery) {
		Delivery del = deliveryRepo.findById(delivery.getDeliveryID()).get();
		
		del.setAddress(delivery.getAddress());
		del.setDeliveryStatus(delivery.getDeliveryStatus());
		del.setProductsList(delivery.getProductsList());
		
	}
}//class
