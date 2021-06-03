/*=============================================================================
    Copyright (c) 2001-2011 Hartmut Kaiser
    Copyright (c) 2001-2011 Joel de Guzman

    Distributed under the Boost Software License, Version 1.0. (See accompanying
    file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
=============================================================================*/
#if !defined(BOOST_SPIRIT_STANDARD_APRIL_26_2006_1106PM)
#define BOOST_SPIRIT_STANDARD_APRIL_26_2006_1106PM

#if defined(_MSC_VER)
#pragma once
#endif

#include <cctype>
<<<<<<< HEAD
=======
#include <boost/assert.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/cstdint.hpp>

namespace boost { namespace spirit { namespace char_encoding
{
    ///////////////////////////////////////////////////////////////////////////
    //  Test characters for specified conditions (using std functions)
    ///////////////////////////////////////////////////////////////////////////
    struct standard
    {
        typedef char char_type;
<<<<<<< HEAD
=======
        typedef unsigned char classify_type;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

        static bool
        isascii_(int ch)
        {
            return 0 == (ch & ~0x7f);
        }

        static bool
        ischar(int ch)
        {
            // uses all 8 bits
            // we have to watch out for sign extensions
<<<<<<< HEAD
            return (0 == (ch & ~0xff) || ~0 == (ch | 0xff)) ? true : false;
=======
            return (0 == (ch & ~0xff) || ~0 == (ch | 0xff)) != 0;
        }

        // *** Note on assertions: The precondition is that the calls to
        // these functions do not violate the required range of ch (int)
        // which is that strict_ischar(ch) should be true. It is the
        // responsibility of the caller to make sure this precondition is not
        // violated.

        static bool
        strict_ischar(int ch)
        {
            // ch should be representable as an unsigned char
            return ch >= 0 && ch <= UCHAR_MAX;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        static bool
        isalnum(int ch)
        {
<<<<<<< HEAD
            return std::isalnum(ch) ? true : false;
=======
            BOOST_ASSERT(strict_ischar(ch));
            return std::isalnum(ch) != 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        static bool
        isalpha(int ch)
        {
<<<<<<< HEAD
            return std::isalpha(ch) ? true : false;
=======
            BOOST_ASSERT(strict_ischar(ch));
            return std::isalpha(ch) != 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        static bool
        isdigit(int ch)
        {
<<<<<<< HEAD
            return std::isdigit(ch) ? true : false;
=======
            BOOST_ASSERT(strict_ischar(ch));
            return std::isdigit(ch) != 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        static bool
        isxdigit(int ch)
        {
<<<<<<< HEAD
            return std::isxdigit(ch) ? true : false;
=======
            BOOST_ASSERT(strict_ischar(ch));
            return std::isxdigit(ch) != 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        static bool
        iscntrl(int ch)
        {
<<<<<<< HEAD
            return std::iscntrl(ch) ? true : false;
=======
            BOOST_ASSERT(strict_ischar(ch));
            return std::iscntrl(ch) != 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        static bool
        isgraph(int ch)
        {
<<<<<<< HEAD
            return std::isgraph(ch) ? true : false;
=======
            BOOST_ASSERT(strict_ischar(ch));
            return std::isgraph(ch) != 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        static bool
        islower(int ch)
        {
<<<<<<< HEAD
            return std::islower(ch) ? true : false;
=======
            BOOST_ASSERT(strict_ischar(ch));
            return std::islower(ch) != 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        static bool
        isprint(int ch)
        {
<<<<<<< HEAD
            return std::isprint(ch) ? true : false;
=======
            BOOST_ASSERT(strict_ischar(ch));
            return std::isprint(ch) != 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        static bool
        ispunct(int ch)
        {
<<<<<<< HEAD
            return std::ispunct(ch) ? true : false;
=======
            BOOST_ASSERT(strict_ischar(ch));
            return std::ispunct(ch) != 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        static bool
        isspace(int ch)
        {
<<<<<<< HEAD
            return std::isspace(ch) ? true : false;
=======
            BOOST_ASSERT(strict_ischar(ch));
            return std::isspace(ch) != 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        static bool
        isblank BOOST_PREVENT_MACRO_SUBSTITUTION (int ch)
        {
<<<<<<< HEAD
=======
            BOOST_ASSERT(strict_ischar(ch));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            return (ch == ' ' || ch == '\t');
        }

        static bool
        isupper(int ch)
        {
<<<<<<< HEAD
            return std::isupper(ch) ? true : false;
=======
            BOOST_ASSERT(strict_ischar(ch));
            return std::isupper(ch) != 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

    ///////////////////////////////////////////////////////////////////////////////
    //  Simple character conversions
    ///////////////////////////////////////////////////////////////////////////////

        static int
        tolower(int ch)
        {
<<<<<<< HEAD
=======
            BOOST_ASSERT(strict_ischar(ch));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            return std::tolower(ch);
        }

        static int
        toupper(int ch)
        {
<<<<<<< HEAD
=======
            BOOST_ASSERT(strict_ischar(ch));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            return std::toupper(ch);
        }

        static ::boost::uint32_t
        toucs4(int ch)
        {
<<<<<<< HEAD
=======
            BOOST_ASSERT(strict_ischar(ch));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            return ch;
        }
    };
}}}

#endif
<<<<<<< HEAD

=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
