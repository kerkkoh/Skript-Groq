package org.kerkkoh.skriptgroq.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kerkkoh.skriptgroq.types.ConversationMessage;

public class ExprGeneratedText extends SimpleExpression<ConversationMessage> {

    static {
        Skript.registerExpression(ExprGeneratedText.class, ConversationMessage.class, ExpressionType.SIMPLE,
                "[the] [last] [gpt] generated prompt");
    }

    public static ConversationMessage conv = new ConversationMessage();

    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed,
            SkriptParser.ParseResult parseResult) {

        return true;
    }

    @Override
    protected ConversationMessage[] get(Event e) {
        ConversationMessage new_conv = new ConversationMessage();
        new_conv.role = "assistant";
        new_conv.content = conv.content;
        // this is always used to retrieve a response, it is always assistant.
        // conv.content is already set from the response of the http request.
        return new ConversationMessage[] { new_conv };
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends ConversationMessage> getReturnType() {
        return ConversationMessage.class;
    }

    public String toString(Event e, boolean debug) {
        return "Last received generated text, as a conversation message.";
    }

}
