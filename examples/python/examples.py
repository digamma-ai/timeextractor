from pytimeextractor import PySettingsBuilder, ExtractionService

# Use default PySettings
text = """Meet journalist Greg Lindsay on Tuesday, September 27, 2011 at 6:30 PM
as he talks about his book,Aerotropolis."""

ExtractionService.extract(text)


# Extract only times from the text using custom PySettings
text = "Catch the post-impressionist exhibit after 19pm! Free organ show every Sunday at 4!"
settings = (PySettingsBuilder()
            .addRulesGroup("TimeGroup")
            .excludeRules("timeRule")
            .build()
           )

ExtractionService.extract(text, settings)
