package com.datamanagement.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepsoitory extends JpaRepository<Schedule, Long> {
}
