Feature: ID FILE CRIF Validation Feature

Scenario Outline: ID - CIS Number Reference Date Format Validation
Given CRIF File is available
When I read the ID_TXT file by line by line
Then I validate the <column_number> field is date format validation in the TXT file for <id_column_number> and "<test_case_name>" 

Examples:
					|column_number|id_column_number|test_case_name|
					|3|1|ID_File - CIS Reference DATE|
					
Scenario Outline: ID - Provider CIS Numner Not Null Validation
Given CRIF File is available
When I read the ID_TXT file by line by line
Then I validate the <column_number> field is Not Null in the TXT file for <id_column_number> and "<test_case_name>"

Examples:
					|column_number|id_column_number|test_case_name|
					|2|1|ID_File - Provider CIS Number|
					
Scenario Outline: ID - Date of Birth Validation
Given CRIF File is available
When I read the ID_TXT file by line by line
Then I validate the <column_number> field is date format validation in the TXT file for <id_column_number> and "<test_case_name>"

Examples:
					|column_number|id_column_number|test_case_name|
					|8|1|ID_File - Date of Birth|
					
Scenario Outline: ID - Duplicate validation
Given CRIF File is available
When I read the ID_TXT file by line by line
Then I validate the <column_number> field is for duplicate validation in the TXT file for <id_column_number> and "<test_case_name>"

Examples:
					|column_number|id_column_number|test_case_name|
					|5|1|ID_File - Duplicate validation|