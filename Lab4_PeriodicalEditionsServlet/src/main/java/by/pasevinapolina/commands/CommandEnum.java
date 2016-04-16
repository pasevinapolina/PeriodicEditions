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
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
