/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class CartObject implements Serializable {

    List<cartDTO> items;

    public List<cartDTO> getItems() {
        return items;
    }

    public void addItem(String id, String name, String color, int year, int quantity, float price, String cateCar, String rentDate, String returnDate) {
        if (items == null) {
            items = new ArrayList<>();
        }
        int mark = 0;
        if (!items.isEmpty()) {
            for (cartDTO item : items) {
                if (item.getIdCar().equals(id) && item.getRentDate().equals(rentDate) && item.getReturnDate().equals(returnDate)) {
                    item.setQuantity(quantity + item.getQuantity());
                    mark = 1;
                }
            }
        } else {
            cartDTO itemCart = new cartDTO(id, name, color, year, quantity, price, cateCar, rentDate, returnDate);
            items.add(itemCart);
            mark = 2;
        }
        if (mark == 0) {
            cartDTO itemCart = new cartDTO(id, name, color, year, quantity, price, cateCar, rentDate, returnDate);
            items.add(itemCart);
        }
    }

    public void removeItem(String id, String rentDate, String returnDate) {
        if (items == null) {
            return;
        }
//        for (cartDTO item : items) {
//            if (item.getIdCar().equals(id) && item.getRentDate().equals(rentDate) && item.getReturnDate().equals(returnDate)) {
//                items.remove(items.get(item));
//            }
//        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getIdCar().equals(id) && items.get(i).getRentDate().equals(rentDate) && items.get(i).getReturnDate().equals(returnDate)) {
                items.remove(i);
            }
        }
        for (cartDTO item : items) {
            System.out.println(item.getNameCar());
        }
//        if(items.containsKey(id)){
//            items.remove(id);
//            if(items.isEmpty()) {
//                items = null;
//            }
//        }
    }
}
