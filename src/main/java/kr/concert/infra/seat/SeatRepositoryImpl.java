package kr.concert.infra.seat;

import kr.concert.domain.seat.Seat;
import kr.concert.domain.seat.SeatRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SeatRepositoryImpl implements SeatRepository {

    private final SeatJpaRepository seatJpaRepository;

    public SeatRepositoryImpl(SeatJpaRepository seatJpaRepository) {
        this.seatJpaRepository = seatJpaRepository;
    }

    @Override
    public List<Seat> findAllBySchedule_ScheduleId(Long scheduleId) {

        return seatJpaRepository.findAllBySchedule_ScheduleId(scheduleId);
    }

    @Override
    public Optional<Seat> findBySeatId(Long seatId) {
        return seatJpaRepository.findBySeatId(seatId);
    }
}
