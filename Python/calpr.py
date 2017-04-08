"""
Print the calendar for a month.
Authors: Levi Shutts and Sydney Lynch 

Limitations: Treats February as always having 28 days. 
"""

import argparse
import datetime # To determine what day of week a month

MONTHLEN = [ 0, # No month zero
	31, # 1. January
	28, # 2. February (ignoring leap years)
	31, # 3. March
	30, # 4. April
	31, # 5. May
	30, # 6. June
	31, # 7. July
	31, # 8. August
	30, # 9. September
	31, #10. October
	30, #11. November
	31, #12. December
	]

parser = argparse.ArgumentParser(description="Print calendar")
parser.add_argument("month", type=int, 
                        help="Month number (1-12)")
parser.add_argument("year", type=int, 
                        help="Year (1800-2525)")
args = parser.parse_args()  # gets arguments from command line
month = args.month
year = args.year


# What day of the week does year,month begin on? 
a_date = datetime.date(year, month, 1)
starts_weekday = a_date.weekday()
## a_date.weekday() gives 0=Monday, 1=Tuesday, etc.
## Roll to start week on Sunday
starts_weekday = (1 + starts_weekday) % 7  


month_day = 1   	    ## Next day to print
last_day = MONTHLEN[month]  ## Last day to print

print(" Su Mo Tu We Th Fr Sa")
###
###   The first (perhaps partial) week
###
for i in range(7):
	if i < starts_weekday :
		print("   ", end="")
	else:
		# Logic for printing one day, moving to next
		print(format(month_day, "3d"), end="")
		month_day += 1
print() # Newline
###
###   Then the full weeks ... 
###
mid_weeks = (last_day - month_day) // 7

for i in range(mid_weeks):
        for i in range(7):
                print(format(month_day, "3d"), end="")
                month_day += 1
        print()
'''
while last_day - month_day >= 6 :
     for i in range(7):
          print()
          month_day += 1
     print()
'''
###
###   Then any remaining days (partial week)
###
last_week = last_day - month_day + 1

for i in range(last_week):
        print(format(month_day, "3d"), end="")
        month_day += 1
