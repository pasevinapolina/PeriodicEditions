package by.pasevinapolina.commands;

/**
 *
 */
public enum CommandEnum {
    EMPTY,
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
            this.command = new SubcriptionCommand();
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

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
