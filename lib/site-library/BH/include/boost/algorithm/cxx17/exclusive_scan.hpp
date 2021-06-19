/*
   Copyright (c) Marshall Clow 2017.

   Distributed under the Boost Software License, Version 1.0. (See accompanying
<<<<<<< HEAD
   file LICENSE10.txt or copy at http://www.boost.org/LICENSE10.txt)
=======
   file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
*/

/// \file  exclusive_scan.hpp
/// \brief ???
/// \author Marshall Clow

#ifndef BOOST_ALGORITHM_EXCLUSIVE_SCAN_HPP
#define BOOST_ALGORITHM_EXCLUSIVE_SCAN_HPP

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

template<class InputIterator, class OutputIterator, class T, class BinaryOperation>
OutputIterator exclusive_scan(InputIterator first, InputIterator last,
                              OutputIterator result, T init, BinaryOperation bOp)
{
    if (first != last)
    {
        T saved = init;
        do
        {
            init = bOp(init, *first);
            *result = saved;
            saved = init;
            ++result;
        } while (++first != last);
    }
    return result;
}

template<class InputIterator, class OutputIterator, class T>
OutputIterator exclusive_scan(InputIterator first, InputIterator last,
                              OutputIterator result, T init)
{
	typedef typename std::iterator_traits<InputIterator>::value_type VT;
<<<<<<< HEAD
    return exclusive_scan(first, last, result, init, std::plus<VT>());
=======
    return boost::algorithm::exclusive_scan(first, last, result, init, std::plus<VT>());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

}} // namespace boost and algorithm

#endif // BOOST_ALGORITHM_EXCLUSIVE_SCAN_HPP
