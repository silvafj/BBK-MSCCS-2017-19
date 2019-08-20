package alarmsystem;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class SecurityControlUnit extends ControlUnit {

    @Autowired
    public SecurityControlUnit(List<Sensor> sensors) {
        super(sensors);
    }

    private boolean canPoll() {
        int hour = LocalDateTime.now().getHour();
        return hour < 6 || hour > 22;
    }

    @Override
    public void pollSensors() {
        if (canPoll()) {
            super.pollSensors();
        }
    }
}
