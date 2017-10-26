import os

project_dir = os.path.dirname(os.path.abspath(__file__))

JAR_FILENAME = "timeextractor-jar-with-dependencies.jar"
PATH_TO_JAR = os.path.join(project_dir, JAR_FILENAME)
JavaSettingsConstructorParams = ['date', 'timezoneOffset', 'rulesToIgnore', 'rulesToInclude', 'includeOnlyLatestDates']

def set_class_path():
    os.environ['CLASSPATH'] = PATH_TO_JAR
