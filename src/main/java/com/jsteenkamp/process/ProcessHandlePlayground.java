package com.jsteenkamp.process;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.stream.Stream;

public class ProcessHandlePlayground
{
    public static void main(String... args) throws Exception
    {
        Stream<ProcessHandle> allProcs = ProcessHandle.allProcesses();

        allProcs.forEach(proc ->
        {
            ProcessHandle.Info info = proc.info();
            Optional<String> user = info.user();
            Optional<String> command = info.command();
            Optional<Instant> startTime = info.startInstant();
            Optional<Duration> duration = info.totalCpuDuration();
            Stream<ProcessHandle> children = proc.descendants();
            Optional<ProcessHandle> parent = proc.parent();

            System.out.println(
                    String.format("(%s) %s  -> %s  | %s | duration : %s | parent: %s",
                            proc.pid(),
                            user.orElse("<none>"),
                            command.orElse("<none>"),
                            startTime.orElse(Instant.EPOCH),
                            duration.map(Duration::toString).orElse("<none>"),
                            parent.map(ProcessHandle::pid).orElse(-1L)
                            ));
        });

    }


}
