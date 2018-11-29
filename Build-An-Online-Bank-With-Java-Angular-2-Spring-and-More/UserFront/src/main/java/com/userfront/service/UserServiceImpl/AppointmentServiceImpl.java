package com.userfront.service.UserServiceImpl;

import com.userfront.dao.AppointmentDao;
import com.userfront.domain.Appointment;
import com.userfront.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentDao appointmentDao;

    public AppointmentServiceImpl(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    public Appointment createAppointment(Appointment appointment) {
       return appointmentDao.save(appointment);
    }

    public List<Appointment> findAll() {
        return appointmentDao.findAll();
    }

    public Optional<Appointment> findAppointment(Long id) {
        return appointmentDao.findById(id);
    }

    public void confirmAppointment(Long id) {
        Optional<Appointment> appointment = findAppointment(id);

//        if (appointment.isPresent()) {
//            Appointment appointmentPresent = appointment.get();
//            appointmentDao.save(appointmentPresent);
//        }

        appointment.ifPresent((value) -> {
            value.setConfirmed(true);
            appointmentDao.save(value);
        });
    }

}
