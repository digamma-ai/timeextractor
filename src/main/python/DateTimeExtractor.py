from .config import PATH_TO_JAR

import os
os.environ['CLASSPATH'] = PATH_TO_JAR

from jnius import autoclass

SettingsBuilder = autoclass('ai.digamma.utils.SettingsBuilder')
Service = autoclass('ai.digamma.service.DateTimeExtractor')
JString = autoclass('java.lang.String')
service = Service()
settings = SettingsBuilder().build()

text = "Reduced entrance fee after 16:30 except for Thursdays. Closed on Mondays."
JText = JString(text)
extracted = Service.extract(JText)
for i,el in enumerate(extracted.toArray()):
    print(i+1, el.toString(), '\n')
