package com.ManagerTourVietNam.service;

import com.ManagerTourVietNam.model.HotelModel.Hotel;
import com.ManagerTourVietNam.repository.HotelRepository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    //danh sach khach san
    public List<Hotel> getAllHotel(){
        return hotelRepository.findAll();
    }


    public Hotel addHotel(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    //sua thong tin khach san
    public Hotel updateHotel(String id, Hotel hotelDetail){
        Optional<Hotel> optionHotel = hotelRepository.findById(id);
        if(optionHotel.isPresent()){
            Hotel hotel=optionHotel.get();
            hotel.setName_hotel(hotelDetail.getName_hotel());
            hotel.setDescription(hotelDetail.getDescription());
            hotel.setImage(hotelDetail.getImage());
            hotel.setStatus(hotelDetail.isStatus());
            return hotelRepository.save(hotel);
        }
        return null;
    }

    //Xoa khach san
    public void deleteHotel(String id){
        hotelRepository.deleteById(id);
    }

    //tim kiem khach san
    public Optional<Hotel> findHotelById(String id){
        return hotelRepository.findById(id);
    }
}
