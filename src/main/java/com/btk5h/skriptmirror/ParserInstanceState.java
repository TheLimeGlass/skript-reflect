package com.btk5h.skriptmirror;

import ch.njol.skript.config.Config;
import ch.njol.skript.lang.parser.ParserInstance;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

public class ParserInstanceState {

  private final Config currentScript;
  private final String currentEventName;
  private final Class<? extends Event>[] currentEvents;
  private final Kleenean hasDelayBefore;

  private ParserInstanceState(Config currentScript,
                              String currentEventName,
                              Class<? extends Event>[] currentEvents,
                              Kleenean hasDelayBefore) {
    this.currentScript = currentScript;
    this.currentEventName = currentEventName;
    this.currentEvents = currentEvents;
    this.hasDelayBefore = hasDelayBefore;
  }

  public void applyToCurrentState() {
    ParserInstance parser = ParserInstance.get();
    parser.setCurrentScript(currentScript);
    parser.setCurrentEvent(currentEventName, currentEvents);
    parser.setHasDelayBefore(hasDelayBefore);
  }

  public static ParserInstanceState copyOfCurrentState() {
    ParserInstance parser = ParserInstance.get();
    return new ParserInstanceState(
      parser.getCurrentScript(),
      parser.getCurrentEventName(),
      parser.getCurrentEvents(),
      parser.getHasDelayBefore()
    );
  }

}
