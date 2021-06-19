// runtime_error.hpp
// Copyright (c) 2007-2009 Ben Hanson (http://www.benhanson.net/)
//
// Distributed under the Boost Software License, Version 1.0. (See accompanying
// file licence_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
#ifndef BOOST_LEXER_RUNTIME_ERROR_HPP
#define BOOST_LEXER_RUNTIME_ERROR_HPP

<<<<<<< HEAD
=======
#include <boost/config.hpp> // for BOOST_SYMBOL_VISIBLE
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <stdexcept>

namespace boost
{
namespace lexer
{
<<<<<<< HEAD
class runtime_error : public std::runtime_error
=======
class BOOST_SYMBOL_VISIBLE runtime_error : public std::runtime_error
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
public:
    runtime_error (const std::string &what_arg_) :
        std::runtime_error (what_arg_)
    {
    }
};
}
}

#endif
