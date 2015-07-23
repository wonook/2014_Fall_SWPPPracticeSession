Given /^I am on my homepage$/ do
  visit('/')
end
When /^I signup as "(\w+)" with "(\w+)"$/ do |name, pwd|
  fill_in('username_input', :with => name)
  fill_in('password_input', :with => pwd)
  click_button('signup_btn')
end
Then /^I see my counter$/ do
  assert page.has_content?("You've logged in")
end
