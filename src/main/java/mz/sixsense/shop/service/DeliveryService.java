package mz.sixsense.shop.service;

import mz.sixsense.shop.entity.Delivery;

public interface DeliveryService {

	public void insertDelivery(Delivery delivery);
	
	public void updateDelivery(Delivery delivery);
	
	public void deleteDelivery(long deliveryID);

	public Delivery getDelivery(long deliveryID);


}
