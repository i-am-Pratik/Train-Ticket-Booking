package com.project.trainticket.config;

import com.project.trainticket.model.*;
import com.project.trainticket.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalTime;
import java.util.Arrays;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            TrainRepository trainRepository,
            ScheduleRepository scheduleRepository,
            CoachRepository coachRepository,
            StationRepository stationRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // Create admin user if not exists
            if (!userRepository.findByUsername("admin").isPresent()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setEmail("admin@trainticket.com");
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }

            // Create schedules and trains only if no trains exist
            if (trainRepository.count() == 0) {
                // Create schedules
                Schedule schedule1 = new Schedule();
                schedule1.setDaysOfWeek("MON,TUE,WED,THU,FRI");
                schedule1.setDepartureTime(LocalTime.of(8, 0));
                schedule1.setArrivalTime(LocalTime.of(14, 0));
                scheduleRepository.save(schedule1);

                Schedule schedule2 = new Schedule();
                schedule2.setDaysOfWeek("SAT,SUN");
                schedule2.setDepartureTime(LocalTime.of(10, 0));
                schedule2.setArrivalTime(LocalTime.of(16, 0));
                scheduleRepository.save(schedule2);

                // Get stations
                Station newDelhi = stationRepository.findById("NDLS").orElseThrow();
                Station mumbai = stationRepository.findById("BCT").orElseThrow();
                Station bangalore = stationRepository.findById("SBC").orElseThrow();
                Station chennai = stationRepository.findById("MAS").orElseThrow();

                // Create trains
                Train train1 = new Train();
                train1.setTrainNumber("TR001");
                train1.setName("Express One");
                train1.setSourceStation(newDelhi);
                train1.setDestinationStation(mumbai);
                train1.setTotalSeats(500);
                train1.setAvailableSeats(500);
                train1.setSchedule(schedule1);
                trainRepository.save(train1);

                Train train2 = new Train();
                train2.setTrainNumber("TR002");
                train2.setName("Weekend Special");
                train2.setSourceStation(bangalore);
                train2.setDestinationStation(chennai);
                train2.setTotalSeats(400);
                train2.setAvailableSeats(400);
                train2.setSchedule(schedule2);
                trainRepository.save(train2);

                // Create coaches for train1
                Arrays.asList(
                    new Object[][]{
                        {"AC1", 50, 3000},
                        {"AC2", 100, 2000},
                        {"AC3", 150, 1500},
                        {"SL", 200, 800}
                    }
                ).forEach(coachData -> {
                    Coach coach = new Coach();
                    coach.setTrain(train1);
                    coach.setCoachType((String) coachData[0]);
                    coach.setTotalSeats((Integer) coachData[1]);
                    coach.setAvailableSeats((Integer) coachData[1]);
                    coach.setFare((Integer) coachData[2]);
                    coachRepository.save(coach);
                });

                // Create coaches for train2
                Arrays.asList(
                    new Object[][]{
                        {"AC1", 40, 2500},
                        {"AC2", 80, 1800},
                        {"AC3", 120, 1200},
                        {"SL", 160, 600}
                    }
                ).forEach(coachData -> {
                    Coach coach = new Coach();
                    coach.setTrain(train2);
                    coach.setCoachType((String) coachData[0]);
                    coach.setTotalSeats((Integer) coachData[1]);
                    coach.setAvailableSeats((Integer) coachData[1]);
                    coach.setFare((Integer) coachData[2]);
                    coachRepository.save(coach);
                });
            }
        };
    }
}
