import os
from numbers import Number
from collections import Iterable, Mapping

from .config import PATH_TO_JAR, JavaSettingsConstructorParams

os.environ['CLASSPATH'] = PATH_TO_JAR

from jnius import autoclass, MetaJavaClass
import jnius

# Java DataTypes
jMap = jnius.autoclass('java.util.HashMap')
jArrayList = jnius.autoclass('java.util.ArrayList')
jList = jnius.autoclass('java.util.List')
jInt = jnius.autoclass('java.lang.Integer')
jLong = jnius.autoclass('java.lang.Long')
jFloat = jnius.autoclass('java.lang.Float')
jDouble = jnius.autoclass('java.lang.Double')
jString = jnius.autoclass('java.lang.String')
jBoolean = jnius.autoclass('java.lang.Boolean')


class JavaPrimitive(object):
    '''
    Convert primitives to their corresponding Java-types based on size
    '''
    def __return_value(self, javaObj, isValue, attr):
        if isValue:
            return getattr(javaObj, attr)()
        else:
            return javaObj


    def __call__(self, obj, isValue = False):
        if isinstance(obj, int):

            if isinstance(obj, bool):
                return jBoolean(obj)

            if obj <= jInt.MAX_VALUE:
                return self.__return_value(jInt(obj), isValue, 'intValue')
            else:
                return self.__return_value(jLong(obj), isValue, 'longValue')

        elif isinstance(obj, float):
            if obj < jFloat.MAX_VALUE:
                return self.__return_value(jFloat(obj), isValue, 'floatValue')
            else:
                return self.__return_value(jDouble(obj), isValue, 'doubleValue')

        elif isinstance(obj, str):
            return jString(obj)


class JavaComposite(object):

    def __init__(self):
        self.primitives = (Number, str)
        self.primitiveConverter = JavaPrimitive()

    def __call__(self, obj, isValue = False):
        '''
        Recursively convert Python objects to composite Java oobjects (e.g. Java Map<String, Object>)
        :param obj: Python object
        '''
        try:
            if isinstance(obj, self.primitives):
                return self.primitiveConverter(obj, isValue)

            elif isinstance(obj.__class__, MetaJavaClass):
                return obj

            elif isinstance(obj, Mapping):
                HashMap = jMap()
                for key, value in obj.items():
                    hashMapKey = self(key, isValue)
                    hashMapValue = self(value, isValue = False)
                    HashMap.put(hashMapKey, hashMapValue)
                return HashMap

            elif isinstance(obj, Iterable):
                JavaArrayList = jArrayList()
                for element in obj:
                    temp = self(element)
                    JavaArrayList.add(temp)
                return JavaArrayList

            else:
                return jString(str(obj))
        except Exception as e:
            print(repr(e))
            raise e


Settings = autoclass('ai.digamma.entities.Settings')


class PySettings(object):
    JavaSettings = Settings

    def __init__(self, **kwargs):
        self.kwargs = kwargs

    def __call__(self):
        if self.kwargs:
            temp = dict()
            for param, value in self.kwargs.items():
                temp[param] = CompositeConverter(value, isValue = True)
            JavaParams = itemgetter(*JavaSettingsConstructorParams)(temp)
            return self.JavaSettings(*JavaParams)
        else:
            return self.JavaSettings()


class Duration(object):

    def __init__(self, durationObj):
        self.durationObj = durationObj

    def get_json_duration(self):
        if not self.durationObj:
            return 'null'
        else:
            return {
                'days': self.durationObj.getDays(),
                'hours': self.durationObj.getHours(),
                'minutes': self.durationObj.getMinutes(),
                'seconds': self.durationObj.getSeconds(),
                'years': self.durationObj.getYears(),
                'weeks': self.durationObj.getWeeks(),
                'months': self.durationObj.getMonths(),
            }


class DurationInterval(object):

    def __init__(self, durationIntervalObj):
        self.durationIntervalObj = durationIntervalObj
        self.__init_durations()

    def __init_durations(self):
        if self.durationIntervalObj:
            self.durationFrom = Duration(self.durationIntervalObj.getDurationFrom())
            self.durationTo = Duration(self.durationIntervalObj.getDurationTo())
        else:
            self.durationFrom = Duration(None)
            self.durationTo = Duration(None)

    def get_json_interval(self):
        return {"durationFrom": self.durationFrom.get_json_duration(),
               "durationTo": self.durationTo.get_json_duration()}


class DayOfWeek(object):

    def __init__(self, dayOfWeekObj):
        self.dayOfWeek = dayOfWeekObj

    def get_value(self):
        if self.dayOfWeek:
            return self.dayOfWeek.getValue()
        else:
            'null'


class WeekOfMonth(object):

    def __init__(self, weekOfMonthObj):
        self.weekOfMonth = weekOfMonthObj

    def get_value(self):
        if self.weekOfMonth:
            return self.weekOfMonth.getValue()
        else:
            'null'


class TimeDate(object):
    def __init__(self, timeDateObj):
        self.timeDate = timeDateObj
        self.__set_fields()

    def __set_fields(self):
        if self.timeDate:
            self.time = self.timeDate.getTime()
            self.date = self.timeDate.getDate()

    def get_json_timedate(self):
        if self.timeDate:
            return {'date': {'year':self.date.getYear(),
                            'month':self.date.getMonth(),
                            'day':self.date.getDay(),
                            'dayOfWeek':DayOfWeek(self.date.getDayOfWeek()).get_value(),
                            'weekOfMonth':WeekOfMonth(self.date.getWeekOfMonth()).get_value()},
                   'time': {'hours':self.time.getHours(),
                           'minutes':self.time.getMinutes(),
                           'seconds':self.time.getMinutes(),
                           'timezoneOffset':self.time.getTimezoneOffset(),
                           'timezoneName':self.time.getTimezoneName()}}
        else:
            return 'null'


class Set(object):
    def __init__(self, setObj):
        self.set = setObj
        self.__set_fields()

    def __set_fields(self):
        if self.set:
            self.frequency= self.set.getFrequency().toString()
            self.daysOfRepetition = self.set.getDaysOfRepetition().toString()
            self.interval = self.set.getInterval()

    def get_json_set(self):
        if self.set:
            return {'frequency': self.frequency,
                   'daysOfRepetition': self.daysOfRepetition,
                   'interval': self.interval}
        else:
            return 'null'


class Temporal(object):
    def __init__(self, temporalObj):
        self.temporal = temporalObj
        self.__set_fields()

    def __set_fields(self):
        if self.temporal:
            self.type = self.temporal.getType().toString()
            self.set = self.temporal.getSet()
            self.duration = self.temporal.getDuration()
            self.durationInterval = self.temporal.getDurationInterval()
            self.startDate = self.temporal.getStartDate()
            self.endDate = self.temporal.getEndDate()

    def get_json_temporal(self):
        return {
            'type': self.type,
            'duration': Duration(self.duration).get_json_duration(),
            'durationInterval': DurationInterval(self.durationInterval).get_json_interval(),
            'set': Set(self.set).get_json_set(),
            'startDate': TimeDate(self.startDate).get_json_timedate(),
            'endDate': TimeDate(self.endDate).get_json_timedate(),
        }


class TemporalExtraction(object):
    def __init__(self, temporalExtractionObj):
        self.temporalExtraction = temporalExtractionObj
        self.__set_fields()


    def __set_fields(self):
        if self.temporalExtraction:
            self.temporalExpresion = self.temporalExtraction.getTemporalExpression()
            self.classOfRuleType = self.temporalExtraction.getClassOfRuleType()
            self.fromPosition = self.temporalExtraction.getFromPosition()
            self.toPosition = self.temporalExtraction.getToPosition()
            self.temporals = self.temporalExtraction.getTemporal().toArray()
            self.confidence = self.temporalExtraction.getConfidence()
            self.locale = self.temporalExtraction.getLocale()

    def get_json_temporal_extraction(self):
        return {
            "temporalExpression": self.temporalExpresion,
            "classOfRuleType": self.classOfRuleType,
            "positions":{"fromPosition": self.fromPosition,
                         "toPosition": self.toPosition},
            "temporals":[Temporal(temporal).get_json_temporal() for temporal in self.temporals],
            'confidence': self.confidence,
            'locale': self.locale.toString()
        }



