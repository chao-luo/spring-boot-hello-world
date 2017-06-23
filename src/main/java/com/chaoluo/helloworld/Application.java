package com.chaoluo.helloworld;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import static com.codahale.metrics.MetricRegistry.name;

@RestController
@EnableAutoConfiguration
public class Application {

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

        };
    }

    @Bean
    MetricRegistry metricRegistry() {
        return new MetricRegistry();
    }

    @Bean
    ConsoleReporter consoleReporter() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry())
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(5, TimeUnit.SECONDS);
        meter().mark();
        return reporter;
    }

    @Bean
    Timer responses() {
        final Timer responses = metricRegistry().timer(name(Application.class, "responses"));
        return responses;
    }

    @Bean
    Meter meter() {
        return metricRegistry().meter("requests");
    }

    @GetMapping("/")
    public String get() {
        final Timer.Context context = responses().time();
        meter().mark();
        context.stop();
        return "hello,world";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}