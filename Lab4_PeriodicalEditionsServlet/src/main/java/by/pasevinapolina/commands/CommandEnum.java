package by.pasevinapolina.commands;

/**
 * Command types enumeration
 */
public enum CommandEnum {
    EMPTY,
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    EDITIONS {
        {
            this.command = new EditionCommand();
        }
    },
    SUBSCRIPTIONS {
        {
            this.command = new SubscriptionCommand();
        }
    },
    PAYMENTS {
        {
            this.command = new PaymentCommand();
        }
    },
    REGISTER_EDITION {
        {
            this.command = new AddEditionCommand();
        }
    }, ADD_PAYMENT {
        {
            this.command = new AddPaymentCommand();
        }
    };

    /**
     * Command from client
     */
    ActionCommand command;

    /**
     * Gets command type
     * @return Command
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
