import os

python_src_dir = os.path.dirname(os.path.abspath(__file__))
project_dir = os.path.join(python_src_dir, os.pardir, os.pardir, os.pardir)

JAR_FILENAME = "timeextractor-jar-with-dependencies.jar"
PATH_TO_JAR = os.path.join(project_dir, "target", JAR_FILENAME)

JavaSettingsConstructorParams = ['date', 'timezoneOffset', 'rulesToIgnore', 'rulesToInclude', 'includeOnlyLatestDates']
