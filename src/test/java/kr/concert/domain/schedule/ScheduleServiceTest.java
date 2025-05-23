package kr.concert.domain.schedule;

import kr.concert.domain.concert.entity.Concert;
import kr.concert.domain.schedule.entity.Schedule;
import kr.concert.domain.schedule.repo.ScheduleRepository;
import kr.concert.domain.schedule.service.ScheduleService;
import kr.concert.interfaces.reservation.ReservationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    @Test
    @DisplayName("해당 콘서트에 스케줄이 없으면 예외가 발생한다.")
    void ifNoSchedulesForConcertCanNotGetList() {
        // given
        Long concertId = 1L;
        given(scheduleRepository.findAllByConcert_ConcertId(concertId)).willReturn(Collections.emptyList());

        // when & then
        assertThatThrownBy(() -> scheduleService.getSchedulesOfConcert(concertId))
                .isInstanceOf(ReservationException.ScheduleNotExistException.class)
                .hasMessage("Schedule Not Exists");
    }

    @Test
    @DisplayName("해당 콘서트에 스케줄이 존재하면 스케줄 리스트를 반환한다.")
    void ifSchedulesExistForConcertCanGetList() {
        // given
        Long concertId = 1L;
        List<Schedule> schedules = List.of(
                new Schedule(1L, new Concert(1L, "콜드플레이 콘서트"), LocalDateTime.of(2025, 5, 1, 19, 0))
        );
        given(scheduleRepository.findAllByConcert_ConcertId(concertId)).willReturn(schedules);

        // when
        List<Schedule> result = scheduleService.getSchedulesOfConcert(concertId);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getScheduleId()).isEqualTo(1L);
        assertThat(result.get(0).getConcert().getConcertId()).isEqualTo(concertId);
    }
}