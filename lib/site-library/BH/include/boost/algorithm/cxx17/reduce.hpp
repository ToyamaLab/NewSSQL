/*
   Copyright (c) Marshall Clow 2017.

   Distributed under the Boost Software License, Version 1.0. (See accompanying
<<<<<<< HEAD
   file LICENSE10.txt or copy at http://www.boost.org/LICENSE10.txt)
=======
   file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
*/

/// \file  reduce.hpp
/// \brief Combine the elements of a sequence into a single value
/// \author Marshall Clow

#ifndef BOOST_ALGORITHM_REDUCE_HPP
#define BOOST_ALGORITHM_REDUCE_HPP

#include <functional>     // for std::plus
#include <iterator>       // for std::iterator_traits

<<<<<<< HEAD
=======
#include <boost/config.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/range/begin.hpp>
#include <boost/range/end.hpp>
#include <boost/range/value_type.hpp>

namespace boost { namespace algorithm {

template<class InputIterator, class T, class BinaryOperation>
T reduce(InputIterator first, InputIterator last, T init, BinaryOperation bOp)
{
    ;
    for (; first != last; ++first)
        init = bOp(init, *first);
    return init;
}

template<class InputIterator, class T>
T reduce(InputIterator first, InputIterator last, T init)
{
	typedef typename std::iterator_traits<InputIterator>::value_type VT;
<<<<<<< HEAD
    return reduce(first, last, init, std::plus<VT>());
=======
    return boost::algorithm::reduce(first, last, init, std::plus<VT>());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

template<class InputIterator>
typename std::iterator_traits<InputIterator>::value_type
reduce(InputIterator first, InputIterator last)
{
<<<<<<< HEAD
    return reduce(first, last,
=======
    return boost::algorithm::reduce(first, last,
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
       typename std::iterator_traits<InputIterator>::value_type());
}

template<class Range>
typename boost::range_value<Range>::type
reduce(const Range &r)
{
<<<<<<< HEAD
    return reduce(boost::begin(r), boost::end(r));
=======
    return boost::algorithm::reduce(boost::begin(r), boost::end(r));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

//	Not sure that this won't be ambiguous (1)
template<class Range, class T>
T reduce(const Range &r, T init)
{
<<<<<<< HEAD
    return reduce(boost::begin (r), boost::end (r), init);
=======
    return boost::algorithm::reduce(boost::begin (r), boost::end (r), init);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}


//	Not sure that this won't be ambiguous (2)
template<class Range, class T, class BinaryOperation>
T reduce(const Range &r, T init, BinaryOperation bOp)
{
<<<<<<< HEAD
    return reduce(boost::begin(r), boost::end(r), init, bOp);
=======
    return boost::algorithm::reduce(boost::begin(r), boost::end(r), init, bOp);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

}} // namespace boost and algorithm

#endif // BOOST_ALGORITHM_REDUCE_HPP
