/*=============================================================================
    Copyright (c) 2006 Tobias Schwinger
    Copyright (c) 2002-2006 Hartmut Kaiser
    http://spirit.sourceforge.net/

  Distributed under the Boost Software License, Version 1.0. (See accompanying
  file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
=============================================================================*/
#if !defined(BOOST_SPIRIT_POSITION_ITERATOR_FWD_HPP)
#define BOOST_SPIRIT_POSITION_ITERATOR_FWD_HPP

#include <string>
<<<<<<< HEAD
#include <boost/detail/iterator.hpp> // for boost::detail::iterator_traits
=======
#include <iterator> // for std::iterator_traits
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/spirit/home/classic/namespace.hpp>
#include <boost/spirit/home/classic/core/nil.hpp>

namespace boost { namespace spirit {

BOOST_SPIRIT_CLASSIC_NAMESPACE_BEGIN

    template <typename String = std::string> 
    struct file_position_base;
    
    typedef file_position_base<std::string> file_position;

    template <typename String = std::string> 
    struct file_position_without_column_base;

    typedef file_position_without_column_base<std::string> file_position_without_column;

    template <
        typename ForwardIteratorT,
        typename PositionT = file_position_base<
            std::basic_string<
<<<<<<< HEAD
                typename boost::detail::iterator_traits<ForwardIteratorT>::value_type
=======
                typename std::iterator_traits<ForwardIteratorT>::value_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            > 
        >,
        typename SelfT = nil_t
    >
    class position_iterator;

    template
    <
        typename ForwardIteratorT,
        typename PositionT = file_position_base<
            std::basic_string<
<<<<<<< HEAD
                typename boost::detail::iterator_traits<ForwardIteratorT>::value_type
=======
                typename std::iterator_traits<ForwardIteratorT>::value_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            > 
        >
    >
    class position_iterator2;

    template <typename PositionT> class position_policy;

BOOST_SPIRIT_CLASSIC_NAMESPACE_END

}} // namespace BOOST_SPIRIT_CLASSIC_NS

#endif

