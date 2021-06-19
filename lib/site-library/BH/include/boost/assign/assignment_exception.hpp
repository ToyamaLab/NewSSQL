// Boost.Assign library
//
//  Copyright Thorsten Ottosen 2003-2004. Use, modification and
//  distribution is subject to the Boost Software License, Version
//  1.0. (See accompanying file LICENSE_1_0.txt or copy at
//  http://www.boost.org/LICENSE_1_0.txt)
//
// For more information, see http://www.boost.org/libs/assign/
//  
 

#ifndef BOOST_ASSIGN_ASSIGNMENT_EXCEPTION_HPP
#define BOOST_ASSIGN_ASSIGNMENT_EXCEPTION_HPP

<<<<<<< HEAD
#if defined(_MSC_VER)
# pragma once
#endif

#include <exception>

=======
#include <boost/config.hpp>
#include <exception>

#if defined(BOOST_HAS_PRAGMA_ONCE)
# pragma once
#endif

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
namespace boost
{    
    namespace assign
    {
        class assignment_exception : public std::exception
        {
        public:
            assignment_exception( const char* _what ) 
            : what_( _what )
            { }
<<<<<<< HEAD
        
            virtual const char* what() const throw()
            {
                return what_;
            }
        
=======

            virtual const char* what() const BOOST_NOEXCEPT_OR_NOTHROW
            {
                return what_;
            }

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        private:
                const char* what_;
        };
    }
}

#endif
