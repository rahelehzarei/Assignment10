package actors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import scala.concurrent.duration.Duration;
import akka.actor.AbstractActorWithTimers;

public class TimeActor extends AbstractActorWithTimers {
   // actor keeps a set of ActorRefs (registered UserActors)

   List<ActorRef> userActors = new ArrayList<>();


   @Override
   public void preStart() {
       getTimers().startPeriodicTimer("Timer", new Tick(), Duration.create(5, TimeUnit.SECONDS));
   }

   static public class RegisterMsg {
   }

   private static final class Tick {
   }

   @Override
   public Receive createReceive() {
       return receiveBuilder()
               .match(RegisterMsg.class, msg -> userActors.add(sender()))
               .match(Tick.class, msg -> notifyClients()).build();
   }

   private void notifyClients(){
       UserActor.TimeMessage tMsg = new UserActor.TimeMessage(LocalDateTime.now().toString());
       userActors.forEach(ar -> ar.tell(tMsg, self()));
   }

}