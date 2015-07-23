class ApplicationController < ActionController::Base
  # Prevent CSRF attacks by raising an exception.
  # For APIs, you may want to use :null_session instead.
  # protect_from_forgery with: :exception
  skip_before_filter :verify_authenticity_token

  def logincheck
    @current_user = nil
    if !session[:userid].nil?
      @current_user = User.find(session[:userid])
    end
  end
end
