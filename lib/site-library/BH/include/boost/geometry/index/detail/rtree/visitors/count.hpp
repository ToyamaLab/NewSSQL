// Boost.Geometry Index
//
// R-tree count visitor implementation
//
// Copyright (c) 2011-2014 Adam Wulkiewicz, Lodz, Poland.
//
<<<<<<< HEAD
=======
// This file was modified by Oracle on 2019.
// Modifications copyright (c) 2019 Oracle and/or its affiliates.
// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle
//
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
// Use, modification and distribution is subject to the Boost Software License,
// Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_GEOMETRY_INDEX_DETAIL_RTREE_VISITORS_COUNT_HPP
#define BOOST_GEOMETRY_INDEX_DETAIL_RTREE_VISITORS_COUNT_HPP

namespace boost { namespace geometry { namespace index {

namespace detail { namespace rtree { namespace visitors {

template <typename Indexable, typename Value>
struct count_helper
{
    template <typename Translator>
    static inline typename Translator::result_type indexable(Indexable const& i, Translator const&)
    {
        return i;
    }
<<<<<<< HEAD
    template <typename Translator>
    static inline bool equals(Indexable const& i, Value const& v, Translator const& tr)
    {
        return geometry::equals(i, tr(v));
=======
    template <typename Translator, typename Strategy>
    static inline bool equals(Indexable const& i, Value const& v, Translator const& tr, Strategy const& s)
    {
        return index::detail::equals<Indexable>::apply(i, tr(v), s);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }
};

template <typename Value>
struct count_helper<Value, Value>
{
    template <typename Translator>
    static inline typename Translator::result_type indexable(Value const& v, Translator const& tr)
    {
        return tr(v);
    }
<<<<<<< HEAD
    template <typename Translator>
    static inline bool equals(Value const& v1, Value const& v2, Translator const& tr)
    {
        return tr.equals(v1, v2);
=======
    template <typename Translator, typename Strategy>
    static inline bool equals(Value const& v1, Value const& v2, Translator const& tr, Strategy const& s)
    {
        return tr.equals(v1, v2, s);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }
};

template <typename ValueOrIndexable, typename Value, typename Options, typename Translator, typename Box, typename Allocators>
struct count
    : public rtree::visitor<Value, typename Options::parameters_type, Box, Allocators, typename Options::node_tag, true>::type
{
<<<<<<< HEAD
    typedef typename rtree::node<Value, typename Options::parameters_type, Box, Allocators, typename Options::node_tag>::type node;
    typedef typename rtree::internal_node<Value, typename Options::parameters_type, Box, Allocators, typename Options::node_tag>::type internal_node;
    typedef typename rtree::leaf<Value, typename Options::parameters_type, Box, Allocators, typename Options::node_tag>::type leaf;

    typedef count_helper<ValueOrIndexable, Value> count_help;

    inline count(ValueOrIndexable const& vori, Translator const& t)
        : value_or_indexable(vori), tr(t), found_count(0)
=======
    typedef typename Options::parameters_type parameters_type;

    typedef typename rtree::node<Value, parameters_type, Box, Allocators, typename Options::node_tag>::type node;
    typedef typename rtree::internal_node<Value, parameters_type, Box, Allocators, typename Options::node_tag>::type internal_node;
    typedef typename rtree::leaf<Value, parameters_type, Box, Allocators, typename Options::node_tag>::type leaf;

    typedef count_helper<ValueOrIndexable, Value> count_help;

    inline count(ValueOrIndexable const& vori, parameters_type const& parameters, Translator const& t)
        : value_or_indexable(vori), m_parameters(parameters), tr(t), found_count(0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {}

    inline void operator()(internal_node const& n)
    {
        typedef typename rtree::elements_type<internal_node>::type elements_type;
        elements_type const& elements = rtree::elements(n);

        // traverse nodes meeting predicates
        for (typename elements_type::const_iterator it = elements.begin();
             it != elements.end(); ++it)
        {
<<<<<<< HEAD
            if ( geometry::covered_by(
                    return_ref_or_bounds(
                        count_help::indexable(value_or_indexable, tr)),
                    it->first) )
=======
            if ( index::detail::covered_by_bounds(count_help::indexable(value_or_indexable, tr),
                                                  it->first,
                                                  index::detail::get_strategy(m_parameters)) )
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            {
                rtree::apply_visitor(*this, *it->second);
            }
        }
    }

    inline void operator()(leaf const& n)
    {
        typedef typename rtree::elements_type<leaf>::type elements_type;
        elements_type const& elements = rtree::elements(n);

        // get all values meeting predicates
        for (typename elements_type::const_iterator it = elements.begin();
             it != elements.end(); ++it)
        {
            // if value meets predicates
<<<<<<< HEAD
            if ( count_help::equals(value_or_indexable, *it, tr) )
=======
            if ( count_help::equals(value_or_indexable, *it, tr,
                                    index::detail::get_strategy(m_parameters)) )
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            {
                ++found_count;
            }
        }
    }

    ValueOrIndexable const& value_or_indexable;
<<<<<<< HEAD
=======
    parameters_type const& m_parameters;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    Translator const& tr;
    typename Allocators::size_type found_count;
};

}}} // namespace detail::rtree::visitors

}}} // namespace boost::geometry::index

#endif // BOOST_GEOMETRY_INDEX_DETAIL_RTREE_VISITORS_COUNT_HPP
