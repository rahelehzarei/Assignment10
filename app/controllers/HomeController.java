package controllers;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.Materializer;
import play.libs.streams.ActorFlow;
import play.mvc.*;
import actors.TimeActor;
import javax.inject.Inject;
import actors.UserActor;
import actors.TimeActor;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
public class HomeController extends Controller {

	@Inject
	private ActorSystem actorSystem;
	@Inject
	private Materializer materializer;

	@Inject public HomeController(ActorSystem system) {
	       system.actorOf(Props.create(TimeActor.class), "timeActor");
	       }

	public WebSocket ws() {

		return WebSocket.Json.accept(request -> ActorFlow.actorRef(UserActor::props, actorSystem, materializer));

	}

	/**
	 * An action that renders an HTML page with a welcome message. The configuration
	 * in the <code>routes</code> file means that this method will be called when
	 * the application receives a <code>GET</code> request with a path of
	 * <code>/</code>.
	 */
	public Result index() {
		return ok(index.render(request()));
	}

}
