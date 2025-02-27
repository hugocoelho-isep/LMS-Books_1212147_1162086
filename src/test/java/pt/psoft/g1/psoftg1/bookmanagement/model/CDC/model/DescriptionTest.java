package pt.psoft.g1.psoftg1.bookmanagement.model.CDC.model;

import org.junit.jupiter.api.Test;
import pt.psoft.g1.psoftg1.bookmanagement.model.Description;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DescriptionTest {

    @Test
    void ensureDescriptionCanBeNull() {
        assertDoesNotThrow(() -> new Description(null));
    }


    /**
     * Text from <a href="https://www.lipsum.com/">Lorem Ipsum</a> generator.
     */
    @Test
    void ensureDescriptionMustNotBeOversize() {
        assertThrows(IllegalArgumentException.class, () -> new Description("\n" +
                "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam venenatis semper nisl, eget condimentum felis tempus vitae. Morbi tempus turpis a felis luctus, ut feugiat tortor mattis. Duis gravida nunc sed augue ultricies tempor. Phasellus ultrices in dolor id viverra. Sed vitae odio ut est vestibulum lacinia sed sed neque. Mauris commodo, leo in tincidunt porta, justo mi commodo arcu, non ultricies ipsum dolor a mauris. Pellentesque convallis vulputate nisl, vel commodo felis ornare nec. Aliquam tristique diam dignissim hendrerit auctor. Mauris nec dolor hendrerit, dignissim urna non, pharetra quam. Sed diam est, convallis nec efficitur eu, sollicitudin ac nibh. In orci leo, dapibus ut eleifend et, suscipit sit amet felis. Integer lectus quam, tristique posuere vulputate sed, tristique eget sem.\n" +
                "\n" +
                "Mauris ac neque porttitor, faucibus velit vel, congue augue. Vestibulum porttitor ipsum eu sem facilisis sagittis. Mauris dapibus tincidunt elit. Phasellus porttitor massa nulla, quis dictum lorem aliquet in. Integer sed turpis in mauris auctor viverra. Suspendisse faucibus tempus tellus, in faucibus urna dapibus at. Nullam dolor quam, molestie nec efficitur nec, bibendum a nunc.\n" +
                "\n" +
                "Maecenas quam arcu, euismod sit amet congue non, venenatis nec ipsum. Cras at posuere metus. Quisque facilisis, sem sit amet vestibulum porta, augue quam semper nulla, eu auctor orci purus vel felis. Fusce ultricies tristique tellus, sed rhoncus elit venenatis id. Aenean in lacus quis ipsum eleifend viverra at at lacus. Nulla finibus, risus ut venenatis posuere, lacus magna eleifend arcu, ut bibendum magna turpis eu lorem. Mauris sed quam eget libero vulputate pretium in in purus. Morbi nec faucibus mi, sit amet pretium tellus. Duis suscipit, tellus id fermentum ultricies, tellus elit malesuada odio, vitae tempor dui purus at ligula. Nam turpis leo, dignissim tristique mauris at, rutrum scelerisque est. Curabitur sed odio sit amet nisi molestie accumsan. Ut vulputate auctor tortor vel ultrices. Nam ut volutpat orci. Etiam faucibus aliquam iaculis.\n" +
                "\n" +
                "Mauris malesuada rhoncus ex nec consequat. Etiam non molestie libero. Phasellus rutrum elementum malesuada. Pellentesque et quam id metus iaculis hendrerit. Fusce molestie commodo tortor ac varius. Etiam ac justo ut lacus semper pretium. Curabitur felis mauris, malesuada accumsan pellentesque vitae, posuere non lacus. Donec sit amet dui finibus, dapibus quam quis, tristique massa. Phasellus velit ipsum, facilisis vel nisi eu, interdum vehicula ante. Nulla eget luctus nunc, nec ullamcorper lectus.\n" +
                "\n" +
                "Curabitur et nisi nisi. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur ultrices ultrices ante eu vestibulum. Phasellus imperdiet non ex sed rutrum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec consequat mauris sed pulvinar sodales. Quisque a iaculis eros. Donec non tellus eget ligula eleifend posuere. Sed tincidunt, purus id eleifend fringilla, tellus erat tristique urna, at ullamcorper purus turpis ac risus. Maecenas non finibus diam. Aliquam erat volutpat. Morbi ultrices blandit arcu eu dignissim. Duis ac dapibus libero. Ut pretium libero sit amet velit viverra semper. Suspendisse vitae augue dui.\n" +
                "\n" +
                "Aliquam aliquam justo porttitor sapien faucibus sollicitudin. Sed iaculis accumsan urna, id elementum est rhoncus vitae. Maecenas rhoncus ultrices arcu eu semper. Integer pulvinar ultricies purus, sit amet scelerisque dui vehicula vel. Phasellus quis urna ac neque auctor scelerisque eget eget arcu. Sed convallis, neque consectetur venenatis ornare, nibh lorem mollis magna, vel vulputate libero ligula egestas ligula. Curabitur iaculis nisl nisi, ac ornare urna lacinia non. Cras sagittis risus sit amet interdum porta. Nam dictum, neque ut blandit feugiat, tortor libero hendrerit enim, at tempor justo velit scelerisque odio. Fusce a ipsum sit amet ligula maximus pharetra. Suspendisse rhoncus leo dolor, vulputate blandit mi ullamcorper ut. Etiam consequat non mi eu porta. Sed mattis metus fringilla purus auctor aliquam.\n" +
                "\n" +
                "Vestibulum quis mi at lorem laoreet bibendum eu porta magna. Etiam vitae metus a sapien sagittis dapibus et et ex. Vivamus sed vestibulum nibh. Etiam euismod odio massa, ac feugiat urna congue ac. Phasellus leo quam, lacinia at elementum vitae, viverra quis ligula. Quisque ultricies tellus nunc, id ultrices risus accumsan in. Vestibulum orci magna, mollis et vehicula non, bibendum et magna. Pellentesque ut nibh quis risus dignissim lacinia sed non elit. Morbi eleifend ipsum posuere velit sollicitudin, quis auctor urna ullamcorper. Praesent pellentesque non lacus eu scelerisque. Praesent quis eros sed orci tincidunt maximus. Quisque imperdiet interdum massa a luctus. Phasellus eget nisi leo.\n" +
                "\n" +
                "Nunc porta nisi eu dui maximus hendrerit eu quis est. Cras molestie lacus placerat, maximus libero hendrerit, eleifend nisi. Suspendisse potenti. Praesent nec mi ut turpis pharetra pharetra. Phasellus pharetra. "));
    }

    @Test
    void ensureDescriptionIsSet() {
        final var description = new Description("Some description");
        assertEquals("Some description", description.toString());
    }

    @Test
    void ensureDescriptionIsChanged() {
        final var description = new Description("Some description");
        description.setDescription("Some other description");
        assertEquals("Some other description", description.toString());
    }
    @Test
    void ensureDescriptionCanBeBlank() {
        var description = new Description("");
        assertNull(description.toString(), "Description should be null if set to a blank string");
    }

    @Test
    void ensureDescriptionWithMaxLengthIsAccepted() {
        String maxLengthDescription = "a".repeat(4096);
        assertDoesNotThrow(() -> new Description(maxLengthDescription));
    }

    @Test
    void ensureDescriptionIsSanitized() {
        var description = new Description("<script>alert('hack');</script>Clean text");
        assertEquals("Clean text", description.toString(), "Description should be sanitized to remove HTML tags");
    }

    @Test
    void ensureDescriptionCanBeResetToNull() {
        var description = new Description("Some description");
        description.setDescription(null);
        assertNull(description.toString(), "Description should be null when reset to null");
    }

    @Test
    void ensureDescriptionSetToEmptyStringBecomesNull() {
        var description = new Description("Initial description");
        description.setDescription("");
        assertNull(description.toString(), "Description should be null when set to an empty string");
    }

    @Test
    void ensureSettingSameValueDoesNotChange() {
        var description = new Description("Same description");
        description.setDescription("Same description");
        assertEquals("Same description", description.toString(), "Setting the same value should not alter description.");
    }

    @Test
    void ensureDescriptionAtMaxLengthAccepted() {
        String maxLengthDescription = "a".repeat(4096);
        assertDoesNotThrow(() -> new Description(maxLengthDescription), "Description at max length should be accepted");
    }

    @Test
    void ensureDescriptionExceedingMaxLengthThrowsException() {
        String overMaxLengthDescription = "a".repeat(4097);
        assertThrows(IllegalArgumentException.class, () -> new Description(overMaxLengthDescription),
                "Description exceeding max length should throw exception");
    }


    @Test
    void ensureMaxLengthEnforcedOnUpdate() {
        var description = new Description("Valid initial description");
        String overMaxLengthDescription = "a".repeat(4097);
        assertThrows(IllegalArgumentException.class, () -> description.setDescription(overMaxLengthDescription),
                "Setting a description exceeding max length should throw exception");
    }

    @Test
    void testSetDescriptionValid() {
        String validDescription = "This is a valid description.";
        Description description = new Description("Initial description");

        description.setDescription(validDescription);

        assertEquals(validDescription, description.toString());
    }


}