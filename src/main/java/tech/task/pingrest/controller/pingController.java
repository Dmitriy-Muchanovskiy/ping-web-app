package tech.task.pingrest.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@RestController
@PropertySource("classpath:application.properties")
public class pingController {

    @Value("${build.version}")
    private String appVersion;

    @GetMapping("/ping")
    public String returnPong(){
        final Message4 message = new Message4();
        message.setText("pong");
        message.setDate(LocalDate.now());
        message.setTime(LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
        message.setVersion(appVersion);
        return message.toString();
    }

    public static class Message4 {
        private String text;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate date;

        @JsonFormat(pattern = "hh:mm:ss a")
        private LocalTime time;

        private String version;

        public void setText(String text) {
            this.text = text;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public void setTime(LocalTime time) {
            this.time = time;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        @Override
        public String toString() {
            return (text + " " + date + " " + time + " " + version);
        }


    }

}
