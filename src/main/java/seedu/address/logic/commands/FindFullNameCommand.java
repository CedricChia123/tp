package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.FullNamePredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindFullNameCommand extends Command {

    public static final String COMMAND_WORD = "findfullname";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds a person whose name "
            + "is the specified name and displays the person's index number.\n"
            + "Parameters: NAME ...\n"
            + "Example: " + COMMAND_WORD + " Alice Yeoh";

    private final FullNamePredicate predicate;

    public FindFullNameCommand(FullNamePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindFullNameCommand // instanceof handles nulls
                && predicate.equals(((FindFullNameCommand) other).predicate)); // state check
    }
}
